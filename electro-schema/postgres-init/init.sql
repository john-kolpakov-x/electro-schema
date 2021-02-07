
ALTER ROLE postgres WITH ENCRYPTED PASSWORD '111';

CREATE USER electro_schema WITH ENCRYPTED PASSWORD '111';
CREATE DATABASE electro_schema WITH OWNER electro_schema;
GRANT ALL ON DATABASE electro_schema TO electro_schema;
