USE [TestDB]
GO

/****** Object:  Table [dbo].[grade]    Script Date: 10/29/2016 10:15:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[grade](
	[studentId] [int] NOT NULL,
	[testId] [int] NOT NULL,
	[courseId] [int] NOT NULL,
	[grade] [nchar](10) NULL,
 CONSTRAINT [PK_grade] PRIMARY KEY CLUSTERED 
(
	[studentId] ASC,
	[testId] ASC,
	[courseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[grade]  WITH CHECK ADD  CONSTRAINT [FK_grade_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[grade] CHECK CONSTRAINT [FK_grade_course]
GO

ALTER TABLE [dbo].[grade]  WITH CHECK ADD  CONSTRAINT [FK_grade_student] FOREIGN KEY([studentId])
REFERENCES [dbo].[student] ([studentId])
GO

ALTER TABLE [dbo].[grade] CHECK CONSTRAINT [FK_grade_student]
GO

ALTER TABLE [dbo].[grade]  WITH CHECK ADD  CONSTRAINT [FK_grade_test] FOREIGN KEY([testId])
REFERENCES [dbo].[test] ([testId])
GO

ALTER TABLE [dbo].[grade] CHECK CONSTRAINT [FK_grade_test]
GO

