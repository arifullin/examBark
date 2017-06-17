-- insert groups
INSERT INTO student_group (id, course, num) VALUES
  (1, 3, 1), (2, 2, 1), (3, 3, 3);

-- insert students, password is 'password'
-- See https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO portal_user (about_me, avatar_url, education, email, name, password, patronymic, phone, sex, social_status, surname, user_role, group_id)
VALUES

  ('About me', 'default.jpg', 'BUDGET', 'lev@zakharov.com', 'Lev',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Отчество', '+7-917-999-99-99', 'MALE',
               'STAROSTA',
               'Zakharov', 'ROLE_STUDENT', 1),

  ('About me', 'default.jpg', 'BUDGET', 'rustam@khakov.com', 'Rustam',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Отчество', '+7-917-999-99-99', 'MALE',
               'STAROSTA',
               'Khakov', 'ROLE_STUDENT', 2),

  ('About me', 'fa186006-c784-3fef-8106-8116bda86688.jpeg', 'CONTRACT', 'ramis@shakirov.com', 'Ramis',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Отчество', '+7-917-999-99-99', 'MALE',
               NULL,
               'Shakirov', 'ROLE_STUDENT', 3),

  ('About me', '453a214f-916f-3712-b65f-0dccd07d092f.jpg', 'CONTRACT', 'gulnara@saha.com', 'Gulnara',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Отчество', '+7-917-999-99-99', 'FEMALE',
               'PROFORG',
               'Shibgareeva', 'ROLE_STUDENT', 3),

  ('About me', '453a214f-916f-3752-b65f-0dccd07d092f.jpeg', null , 'marat@arslanov.com', 'Marat',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Mirzaevich', '+7-917-999-99-99', 'MALE',
               NULL,
               'Arslanov', 'ROLE_TEACHER', null),

  ('About me', '453a214f-916f-3752-b65f-0dccd07d092a.jpeg', null , 'marat@nasr.com', 'Marat',
               '$2a$12$7DsfOtowFQwC2p7LESJ5HeXOnlDPJquES82vW8/hm3a20S1mrGgve', 'Отчество', '+7-917-999-99-99', 'MALE',
               NULL,
               'Nasrutdinov', 'ROLE_WORKER', null);


