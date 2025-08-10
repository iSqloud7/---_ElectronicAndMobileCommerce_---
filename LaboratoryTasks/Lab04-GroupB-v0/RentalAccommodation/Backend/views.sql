CREATE materialized VIEW bookings_per_host AS
SELECT b.host_id AS host_id, COUNT(b.ID) AS num_of_bookings
FROM BOOKINGS as b
GROUP BY b.host_id;

CREATE materialized VIEW hosts_per_country AS
SELECT h.country_id AS country_id, COUNT(h.ID) AS num_of_hosts
FROM HOSTS AS h
GROUP BY h.country_id;