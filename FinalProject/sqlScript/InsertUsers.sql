USE [TestDB]
GO

INSERT INTO [dbo].[user]
           ([userName]
           ,[password]
           ,[role]
           )
     VALUES
           ('user1'
           ,'pass'
           ,'student'
           )
GO
INSERT INTO [dbo].[user]
           ([userName]
           ,[password]
           ,[role]
           )
     VALUES
           ('user2'
           ,'pass1'
           ,'teacher'
           )
GO
INSERT INTO [dbo].[user]
           ([userName]
           ,[password]
           ,[role]
           )
     VALUES
           ('user3'
           ,'pass2'
           ,'teachingAssistant'
           )
GO


