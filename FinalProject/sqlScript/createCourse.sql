USE [TestDB]
GO

/****** Object:  Table [dbo].[course]    Script Date: 10/29/2016 10:15:19 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[course](
	[courseId] [int] NOT NULL,
	[courseName] [nvarchar](50) NULL,
 CONSTRAINT [PK_course] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

