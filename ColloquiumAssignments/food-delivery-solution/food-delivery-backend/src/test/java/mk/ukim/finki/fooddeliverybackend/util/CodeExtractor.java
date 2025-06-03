package mk.ukim.finki.fooddeliverybackend.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CodeExtractor {

    public static void submitSourcesAndLogs() throws JsonProcessingException, UnknownHostException {
        File current = new File(".").getAbsoluteFile();
        File projectRoot = findProjectRoot(current);

        if (projectRoot == null) {
            System.err.println("Could not find project root.");
            return;
        }

        Map<String, String> allSources = new TreeMap<>();

        File backendSrcMain = findFirstMatchingFolder(projectRoot, "-backend/src/main");
        if (backendSrcMain != null) {
            allSources.putAll(readFilesContent(findFilesWithExtensions(backendSrcMain, List.of(".java", ".properties", ".html"))));
        }

        File frontendSrc = findFirstMatchingFolder(projectRoot, "-frontend/src");
        if (frontendSrc != null) {
            allSources.putAll(readFilesContent(findFilesWithExtensions(frontendSrc, List.of(".js", ".jsx", ".ts", ".tsx", ".css", ".scss", ".html"))));
        }

        SubmissionHelper.submitSource(allSources);
    }

    private static File findProjectRoot(File current) {
        while (current != null) {
            File[] children = current.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isDirectory() && child.getName().endsWith("-backend")) {
                        File srcMain = new File(child, "src/main");
                        if (srcMain.exists()) return current;
                    }
                }
            }
            current = current.getParentFile();
        }
        return null;
    }

    private static File findFirstMatchingFolder(File root, String pathSuffix) {
        File[] children = root.listFiles();
        if (children == null) return null;

        for (File f : children) {
            if (f.isDirectory() && f.getName().endsWith(pathSuffix.split("/")[0])) {
                File target = new File(f, pathSuffix.substring(pathSuffix.indexOf("/") + 1));
                if (target.exists()) return target;
            }
        }
        return null;
    }

    public static List<File> findFilesWithExtensions(File root, List<String> extensions) {
        List<File> result = new ArrayList<>();
        if (root == null || !root.exists()) return result;

        File[] files = root.listFiles();
        if (files == null) return result;

        for (File f : files) {
            if (f.isDirectory()) {
                result.addAll(findFilesWithExtensions(f, extensions));
            } else {
                for (String ext : extensions) {
                    if (f.getName().endsWith(ext)) {
                        result.add(f);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, String> readFilesContent(List<File> files) {
        Map<String, String> fileContent = new TreeMap<>();

        for (File f : files) {
            try {
                String content = readString(f.getAbsolutePath());
                String absolute = f.getAbsolutePath().replace("\\", "/");

                int index = absolute.lastIndexOf("/src/");
                if (index != -1) {
                    String beforeSrc = absolute.substring(0, index);
                    String projectName = beforeSrc.substring(beforeSrc.lastIndexOf("/") + 1);

                    String fromSrc = projectName + absolute.substring(index);
                    fileContent.put(fromSrc, content);
                } else {
                    System.err.println("Skipping file (no /src/): " + absolute);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    private static String readString(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null)
                builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
