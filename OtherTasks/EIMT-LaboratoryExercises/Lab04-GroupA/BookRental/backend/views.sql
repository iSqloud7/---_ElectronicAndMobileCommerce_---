CREATE
MATERIALIZED VIEW books_per_author AS
SELECT b.author_id as author_id, COUNT(b.id) as number_of_books
FROM BOOKS AS b
GROUP BY b.author_id;

CREATE
MATERIALIZED VIEW authors_per_country AS
SELECT a.country_id AS country_id, COUNT(a.id) AS number_of_authors
FROM AUTHORS AS a
GROUP BY a.country_id;