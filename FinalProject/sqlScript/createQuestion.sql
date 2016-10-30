USE [TestDB]
GO

/****** Object:  Table [dbo].[question]    Script Date: 10/29/2016 10:15:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[question](
	[questionId] [int] NOT NULL,
	[question] [nvarchar](max) NOT NULL,
	[optionA] [nvarchar](max) NOT NULL,
	[optionB] [nvarchar](max) NOT NULL,
	[optionC] [nvarchar](max) NOT NULL,
	[optionD] [nvarchar](max) NOT NULL,
	[answer] [nchar](10) NOT NULL,
	[courseId] [int] NOT NULL,
 CONSTRAINT [PK_question] PRIMARY KEY CLUSTERED 
(
	[questionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[question]  WITH CHECK ADD  CONSTRAINT [FK_question_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[question] CHECK CONSTRAINT [FK_question_course]
GO

