USE [TestDB]
GO

/****** Object:  Table [dbo].[assists]    Script Date: 10/29/2016 10:15:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[assists](
	[teachingAssistantId] [int] NOT NULL,
	[courseId] [int] NOT NULL,
 CONSTRAINT [PK_assists] PRIMARY KEY CLUSTERED 
(
	[teachingAssistantId] ASC,
	[courseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[assists]  WITH CHECK ADD  CONSTRAINT [FK_assists_course1] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[assists] CHECK CONSTRAINT [FK_assists_course1]
GO

ALTER TABLE [dbo].[assists]  WITH CHECK ADD  CONSTRAINT [FK_assists_teachingAssistant1] FOREIGN KEY([teachingAssistantId])
REFERENCES [dbo].[teachingAssistant] ([teachingAssistantId])
GO

ALTER TABLE [dbo].[assists] CHECK CONSTRAINT [FK_assists_teachingAssistant1]
GO

