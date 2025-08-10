import axiosInstance from "../Axios/axios"

// sync -> (СИНХРОНО)
// кога ќе се прати повик до нешто (пр. backend), 
// корисникот не може ништо друго да прави на апликацијата, 
// се додека не се добие response од тоа нешто (пр. backend).

// async -> (АСИНХРОНО)
// кога ќе се прати повик до нешто (пр. backend),
// корисникот може да прави други работи на апликацијата,
// не мора да чека response од тоа нешто (пр. backend).

const userRepository = {
    findAll: async () => {
        return await axiosInstance.get('/users')
    },
}

export default userRepository