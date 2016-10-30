USE [TestDB]
GO

/****** Object:  Table [dbo].[test]    Script Date: 10/29/2016 10:17:20 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[test](
	[testId] [int] NOT NULL,
	[testName] [nvarchar](50) NOT NULL,
	[time] [int] NOT NULL,
	[courseId] [int] NOT NULL,
 CONSTRAINT [PK_test] PRIMARY KEY CLUSTERED 
(
	[testId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[test]  WITH CHECK ADD  CONSTRAINT [FK_test_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[test] CHECK CONSTRAINT [FK_test_course]
GO

