create table appointments(
id varchar(10) primary key,
appointment_day date not null,
appointment_time time not null,
patient_id varchar(10) not null,
worker_id varchar(10) not null
);