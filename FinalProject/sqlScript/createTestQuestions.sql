USE [TestDB]
GO

/****** Object:  Table [dbo].[testQuestions]    Script Date: 10/29/2016 10:17:34 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[testQuestions](
	[testId] [int] NOT NULL,
	[questionId] [int] NOT NULL,
 CONSTRAINT [PK_testQuestions] PRIMARY KEY CLUSTERED 
(
	[testId] ASC,
	[questionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[testQuestions]  WITH CHECK ADD  CONSTRAINT [FK_testQuestions_question] FOREIGN KEY([questionId])
REFERENCES [dbo].[question] ([questionId])
GO

ALTER TABLE [dbo].[testQuestions] CHECK CONSTRAINT [FK_testQuestions_question]
GO

ALTER TABLE [dbo].[testQuestions]  WITH CHECK ADD  CONSTRAINT [FK_testQuestions_test] FOREIGN KEY([testId])
REFERENCES [dbo].[test] ([testId])
GO

ALTER TABLE [dbo].[testQuestions] CHECK CONSTRAINT [FK_testQuestions_test]
GO

