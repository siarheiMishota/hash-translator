insert into users (email, first_name, last_name, password, role)
values ('admin@gmail.com','admin','adminov','$2a$12$WBrum4EpF7NxWMce43n9MOTQyPL2UllMqI7Pi1ZzXFlac3gdVf7RK','ADMIN'),
       ('service@gmail.com', 'service','servicov','$2a$12$2XfUAZ6RAj95XsBplu7Yxu3VBGSRGpwQ1bM1oYhG5EPcSCLVSPIpK','SERVICE'),
       ('user@gmail.com', 'user','userov','$2a$12$6C7gusijmMiDE.VQQd8rlObB.DPiMv1J8biP84qE7x1p56L2hlD2C','USER');
insert into users ( email, first_name, last_name, password, role, status)
values ('ban@gmail.com','ban','bannov','$2a$12$6/WA4h9uoq0e1/25LkvYA.RyZxV8flD/7qzj6f13/Hc931htrGamu', 'USER','BANNED');