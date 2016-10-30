USE [TestDB]
GO

/****** Object:  Table [dbo].[takes]    Script Date: 10/29/2016 10:16:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[takes](
	[studentId] [int] NOT NULL,
	[courseId] [int] NOT NULL,
 CONSTRAINT [PK_takes] PRIMARY KEY CLUSTERED 
(
	[studentId] ASC,
	[courseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[takes]  WITH CHECK ADD  CONSTRAINT [FK_takes_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[takes] CHECK CONSTRAINT [FK_takes_course]
GO

ALTER TABLE [dbo].[takes]  WITH CHECK ADD  CONSTRAINT [FK_takes_student] FOREIGN KEY([studentId])
REFERENCES [dbo].[student] ([studentId])
GO

ALTER TABLE [dbo].[takes] CHECK CONSTRAINT [FK_takes_student]
GO

