create table user (
    username varchar(50),
    password varchar(50),
    is_deleted boolean,
    primary key (username)
);

create table user_role (
    role_id varchar(50),
    username varchar(50),
    user_role varchar(50),
    primary key (role_id)
);

drop table child_service;
create table child_service (
    id int not null AUTO_INCREMENT,
    parent_service_id int,
    name varchar(100),
    intro varchar (255),
    image text,
    is_shown_home boolean,
    url varchar(255),
    content text,
    created_date datetime,
    updated_date datetime,
    is_deleted boolean,
    primary key (id)
);


drop table micellaneous_text;
create table micellaneous_text (
    id int not null AUTO_INCREMENT,
    page varchar(255),
    is_shown_home boolean,
    section varchar(255),
    title varchar(255),
    content text,
    display_order int,
    created_date datetime,
    updated_date datetime,
    is_deleted boolean,
    primary key (id)
);

drop table post;
create table post (
    id int not null AUTO_INCREMENT,
    name varchar(100),
    intro varchar (255),
    image text,
    url varchar(255),
    content text,
    service_id int,
    created_date datetime,
    updated_date datetime,
    is_deleted boolean,
    primary key (id)
);

drop table service;
create table service (
    id int not null AUTO_INCREMENT,
    name varchar(100),
    intro varchar (255),
    image text,
    url varchar(255),
    display_order int,
    content text,
    created_date datetime,
    updated_date datetime,
    is_deleted boolean,
    primary key (id)
);

insert into test.child_service(parent_service_id,name,intro,image,is_shown_home,url,content,created_date,updated_date,is_deleted) values (1,'Demo Sub Service AA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua','iVBORw0KGgoAAAANSUhEUgAAAMgAAADIBAMAAABfdrOtAAAAG1BMVEWMCwumAACSCAifAgKiAQGZBQWcBASPCQmVBgb3Qq1lAAAACXBIWXMAAA7EAAAOxAGVKw4bAAABrUlEQVR4nO3UT0/CMBjH8ccx2I56IFynkHg1SgxHHCocSfQFGKP3+e++xL1wn7bPUCAeKF5Mvp+EluX3ZN3ariIAAAAAAAAAAAAAAAAA/q2TwrXZib94LTbj5GdgVbtKxhdXS+2uL270ajQbL9fz4WzcXwVWtbNeIdmt3qSQtwdJSsku1/NHkfdVEKriHFey0G4haS3+ty4ZtEGoipMW+VS7T2m0zc+28tICq4rTqXtuJV7kWdvsUJtuoc1Hm08ssKo4B1Wn1i6tJu5qrj9dA8lWEzOQEFhV3CCNTph2naJ0V+eu0SV+ry3WWQqBVcUNsgiP16ndS4SnzuffL5LWEgKrihqje7Y9iDTN6mZ38geDNNX2dEm338b5XPafrmRuj/dj4fULfGoXeFTJ/guvayybW1i3Vl7aM7h+3y2c+y07FfeZjaT9GHVrNYXPG/fkIbCqCPf+9d1WKiWtJSyP21r+FaTrZ8+CULW7XliCUe0PyIUdkD29qQzdv7A0FoSq3R0fqaU78d0hPtw86hMX99vAqqJlp757/W3vhMCqAAAAAAAAAAAAAAAAAPxbX82/SILlk9xfAAAAAElFTkSuQmCC','1','serviceaa','At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.','2/1/2022 1:47:20 PM','2/1/2022 1:47:20 PM','0');

insert into test.service(name,intro,image,url,display_order,content,created_date,updated_date,is_deleted) values ('Demo Service A',null,'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIBAMAAABfdrOtAAAAG1BMVEWMCwumAACSCAifAgKiAQGZBQWcBASPCQmVBgb3Qq1lAAAACXBIWXMAAA7EAAAOxAGVKw4bAAABrUlEQVR4nO3UT0/CMBjH8ccx2I56IFynkHg1SgxHHCocSfQFGKP3+e++xL1wn7bPUCAeKF5Mvp+EluX3ZN3ariIAAAAAAAAAAAAAAAAA/q2TwrXZib94LTbj5GdgVbtKxhdXS+2uL270ajQbL9fz4WzcXwVWtbNeIdmt3qSQtwdJSsku1/NHkfdVEKriHFey0G4haS3+ty4ZtEGoipMW+VS7T2m0zc+28tICq4rTqXtuJV7kWdvsUJtuoc1Hm08ssKo4B1Wn1i6tJu5qrj9dA8lWEzOQEFhV3CCNTph2naJ0V+eu0SV+ry3WWQqBVcUNsgiP16ndS4SnzuffL5LWEgKrihqje7Y9iDTN6mZ38geDNNX2dEm338b5XPafrmRuj/dj4fULfGoXeFTJ/guvayybW1i3Vl7aM7h+3y2c+y07FfeZjaT9GHVrNYXPG/fkIbCqCPf+9d1WKiWtJSyP21r+FaTrZ8+CULW7XliCUe0PyIUdkD29qQzdv7A0FoSq3R0fqaU78d0hPtw86hMX99vAqqJlp757/W3vhMCqAAAAAAAAAAAAAAAAAPxbX82/SILlk9xfAAAAAElFTkSuQmCC','serviceA',null,'<p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</span><br></p>','1/30/2022 8:04:21 PM','1/30/2022 8:09:42 PM','0');
insert into test.service(name,intro,image,url,display_order,content,created_date,updated_date,is_deleted) values ('Demo Service B',null,'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIBAMAAABfdrOtAAAAG1BMVEWMCwumAACSCAifAgKiAQGZBQWcBASPCQmVBgb3Qq1lAAAACXBIWXMAAA7EAAAOxAGVKw4bAAABrUlEQVR4nO3UT0/CMBjH8ccx2I56IFynkHg1SgxHHCocSfQFGKP3+e++xL1wn7bPUCAeKF5Mvp+EluX3ZN3ariIAAAAAAAAAAAAAAAAA/q2TwrXZib94LTbj5GdgVbtKxhdXS+2uL270ajQbL9fz4WzcXwVWtbNeIdmt3qSQtwdJSsku1/NHkfdVEKriHFey0G4haS3+ty4ZtEGoipMW+VS7T2m0zc+28tICq4rTqXtuJV7kWdvsUJtuoc1Hm08ssKo4B1Wn1i6tJu5qrj9dA8lWEzOQEFhV3CCNTph2naJ0V+eu0SV+ry3WWQqBVcUNsgiP16ndS4SnzuffL5LWEgKrihqje7Y9iDTN6mZ38geDNNX2dEm338b5XPafrmRuj/dj4fULfGoXeFTJ/guvayybW1i3Vl7aM7h+3y2c+y07FfeZjaT9GHVrNYXPG/fkIbCqCPf+9d1WKiWtJSyP21r+FaTrZ8+CULW7XliCUe0PyIUdkD29qQzdv7A0FoSq3R0fqaU78d0hPtw86hMX99vAqqJlp757/W3vhMCqAAAAAAAAAAAAAAAAAPxbX82/SILlk9xfAAAAAElFTkSuQmCC','serviceB',null,'<p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</span><br></p>','1/30/2022 8:04:46 PM','2/1/2022 2:01:14 PM','0');
insert into test.service(name,intro,image,url,display_order,content,created_date,updated_date,is_deleted) values ('Demo Service C',null,'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIBAMAAABfdrOtAAAAG1BMVEWMCwumAACSCAifAgKiAQGZBQWcBASPCQmVBgb3Qq1lAAAACXBIWXMAAA7EAAAOxAGVKw4bAAABrUlEQVR4nO3UT0/CMBjH8ccx2I56IFynkHg1SgxHHCocSfQFGKP3+e++xL1wn7bPUCAeKF5Mvp+EluX3ZN3ariIAAAAAAAAAAAAAAAAA/q2TwrXZib94LTbj5GdgVbtKxhdXS+2uL270ajQbL9fz4WzcXwVWtbNeIdmt3qSQtwdJSsku1/NHkfdVEKriHFey0G4haS3+ty4ZtEGoipMW+VS7T2m0zc+28tICq4rTqXtuJV7kWdvsUJtuoc1Hm08ssKo4B1Wn1i6tJu5qrj9dA8lWEzOQEFhV3CCNTph2naJ0V+eu0SV+ry3WWQqBVcUNsgiP16ndS4SnzuffL5LWEgKrihqje7Y9iDTN6mZ38geDNNX2dEm338b5XPafrmRuj/dj4fULfGoXeFTJ/guvayybW1i3Vl7aM7h+3y2c+y07FfeZjaT9GHVrNYXPG/fkIbCqCPf+9d1WKiWtJSyP21r+FaTrZ8+CULW7XliCUe0PyIUdkD29qQzdv7A0FoSq3R0fqaU78d0hPtw86hMX99vAqqJlp757/W3vhMCqAAAAAAAAAAAAAAAAAPxbX82/SILlk9xfAAAAAElFTkSuQmCC','serviceC',null,'<p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</span><br></p>','2/1/2022 9:08:42 AM','2/1/2022 9:08:42 AM','0');

insert into test.`user`(username,password,is_deleted) values ('thai.nguyenquoc','1234@Tpszz','0');

insert into test.user_role(role_id,username,user_role) values ('1','thai.nguyenquoc','admin');

insert into test.post(name,intro,image,url,content,service_id,created_date,updated_date,is_deleted) values ('Demo news demo news demo news demo news demo news demo news','At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum','iVBORw0KGgoAAAANSUhEUgAAAMgAAADIBAMAAABfdrOtAAAAG1BMVEWMCwumAACSCAifAgKiAQGZBQWcBASPCQmVBgb3Qq1lAAAACXBIWXMAAA7EAAAOxAGVKw4bAAABrUlEQVR4nO3UT0/CMBjH8ccx2I56IFynkHg1SgxHHCocSfQFGKP3+e++xL1wn7bPUCAeKF5Mvp+EluX3ZN3ariIAAAAAAAAAAAAAAAAA/q2TwrXZib94LTbj5GdgVbtKxhdXS+2uL270ajQbL9fz4WzcXwVWtbNeIdmt3qSQtwdJSsku1/NHkfdVEKriHFey0G4haS3+ty4ZtEGoipMW+VS7T2m0zc+28tICq4rTqXtuJV7kWdvsUJtuoc1Hm08ssKo4B1Wn1i6tJu5qrj9dA8lWEzOQEFhV3CCNTph2naJ0V+eu0SV+ry3WWQqBVcUNsgiP16ndS4SnzuffL5LWEgKrihqje7Y9iDTN6mZ38geDNNX2dEm338b5XPafrmRuj/dj4fULfGoXeFTJ/guvayybW1i3Vl7aM7h+3y2c+y07FfeZjaT9GHVrNYXPG/fkIbCqCPf+9d1WKiWtJSyP21r+FaTrZ8+CULW7XliCUe0PyIUdkD29qQzdv7A0FoSq3R0fqaU78d0hPtw86hMX99vAqqJlp757/W3vhMCqAAAAAAAAAAAAAAAAAPxbX82/SILlk9xfAAAAAElFTkSuQmCC','My Demo News','<p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</span></p><p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</span></p><p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</span></p><p><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</span><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;"><br></span><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;"><br></span><span style="font-family: &quot;Open Sans&quot;, Arial, sans-serif; text-align: justify;"><br></span><br></p>',1,'2/1/2022 2:21:16 PM','2/1/2022 2:21:16 PM','0');

