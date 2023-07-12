USE Portfolio_Database

CREATE TABLE [user_details] (
    [user_id] int IDENTITY(1,1) NOT NULL,
    [salutation] varchar(5) NOT NULL,
    [first_name] varchar(25) NOT NULL,
    [last_name] varchar(40) NULL,
    [date_of_birth] Date NULL,
    [email] varchar(100) NOT NULL,
    [address] int NOT NULL,
    PRIMARY KEY ([user_id])
);

CREATE TABLE [address] (
    [address_id] int IDENTITY(1,1) NOT NULL,
    [city] varchar(25) NOT NULL,
    [state] varchar(30) NULL,
    [country] varchar(30) NOT NULL,
    PRIMARY KEY ([address_id])
);

CREATE TABLE [career_summary] (
    [summary_id] int IDENTITY(1,1) NOT NULL,
    [user_id] int NOT NULL,
    [objective] text NOT NULL,
    PRIMARY KEY ([summary_id])
);

CREATE TABLE [social_profiles] (
    [user_id] int NOT NULL,
    [media_key] int NOT NULL,
    [profile_link] varchar(max) NOT NULL,
    PRIMARY KEY ([user_id], [media_key])
);

CREATE TABLE [social_media] (
    [media_key] int IDENTITY(1,1) NOT NULL,
    [social_media_type] Varchar(40) NOT NULL,
    PRIMARY KEY ([media_key])
);

CREATE TABLE [projects] (
    [project_id] int IDENTITY(1,1) NOT NULL,
    [user_id] int NOT NULL,
    [name] varchar(30) NOT NULL,
    [description] text NULL,
    [technologies_used] text NULL,
    [related_link] varchar(max) NULL,
    PRIMARY KEY ([project_id])
);

CREATE TABLE [work_experience] (
    [exp_key] int IDENTITY(1,1) NOT NULL,
    [user_id] int NOT NULL,
    [company] varchar(50) NOT NULL,
    [start_date] date NOT NULL,
    [end_date] date NOT NULL,
    [designation] varchar(50) NOT NULL,
    [description] text NOT NULL,
    PRIMARY KEY ([exp_key])
);

CREATE TABLE [awards_certifications] (
    [id] int IDENTITY(1,1) NOT NULL,
    [user_id] int NOT NULL,
    [name] varchar(50) NOT NULL,
    [description] text NULL,
    [related_link] varchar(max) NULL,
    [type] varchar(10) NOT NULL CHECK ([type] IN ('cert', 'award')),
    PRIMARY KEY ([id])
);

CREATE TABLE [user_skill] (
    [skill_id] int IDENTITY(1,1) NOT NULL,
    [user_id] int NOT NULL,
    PRIMARY KEY ([skill_id], [user_id])
);

CREATE TABLE [skills] (
    [skill_id] int IDENTITY(1,1) NOT NULL,
    [name] varchar(50) NOT NULL,
    PRIMARY KEY ([skill_id])
);

CREATE TABLE [user_phone] (
    [contact_number] varchar(20) NOT NULL,
    [user_id] int NOT NULL,
    [type] varchar(20) NOT NULL CHECK ([type] IN ('primary', 'secondary')),
    PRIMARY KEY ([contact_number])
);

ALTER TABLE [career_summary] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [social_profiles] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [social_profiles] ADD FOREIGN KEY ([media_key]) REFERENCES [social_media] ([media_key]);

ALTER TABLE [projects] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [work_experience] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [awards_certifications] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [user_skill] ADD FOREIGN KEY ([skill_id]) REFERENCES [skills] ([skill_id]);

ALTER TABLE [user_skill] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [user_phone] ADD FOREIGN KEY ([user_id]) REFERENCES [user_details] ([user_id]);

ALTER TABLE [user_details] ADD FOREIGN KEY ([address]) REFERENCES [address] ([address_id]);
