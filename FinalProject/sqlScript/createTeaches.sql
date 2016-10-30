USE [TestDB]
GO

/****** Object:  Table [dbo].[teaches]    Script Date: 10/29/2016 10:16:51 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[teaches](
	[teacherId] [int] NOT NULL,
	[courseId] [int] NOT NULL,
 CONSTRAINT [PK_teaches] PRIMARY KEY CLUSTERED 
(
	[teacherId] ASC,
	[courseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[teaches]  WITH CHECK ADD  CONSTRAINT [FK_teaches_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([courseId])
GO

ALTER TABLE [dbo].[teaches] CHECK CONSTRAINT [FK_teaches_course]
GO

ALTER TABLE [dbo].[teaches]  WITH CHECK ADD  CONSTRAINT [FK_teaches_teacher] FOREIGN KEY([teacherId])
REFERENCES [dbo].[teacher] ([teacherId])
GO

ALTER TABLE [dbo].[teaches] CHECK CONSTRAINT [FK_teaches_teacher]
GO

