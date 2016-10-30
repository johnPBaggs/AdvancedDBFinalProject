USE [TestDB]
GO

/****** Object:  Table [dbo].[teachingAssistant]    Script Date: 10/29/2016 10:17:08 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[teachingAssistant](
	[teachingAssistantId] [int] NOT NULL,
	[teachingAssistantName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_teachingAssistant] PRIMARY KEY CLUSTERED 
(
	[teachingAssistantId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

