create table ARTEFACT (
ID UUID primary key,
Created DATE,
UserID VARCHAR2(20),
Category VARCHAR2(20),
Description VARCHAR2(20),
isOriginal BOOLEAN (20)
);

create table COMMENT (
ID UUID primary key,
ArtefactID UUID,
UserID VARCHAR2(20),
Content VARCHAR2(20),
isOriginal BOOLEAN (20)
);