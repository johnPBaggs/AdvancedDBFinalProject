USE [TestDB]
GO

/****** Object:  Table [dbo].[user]    Script Date: 10/29/2016 8:26:24 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user](
	[userName] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[role] [nvarchar](50) NOT NULL,
	[id] [int] NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_roles] FOREIGN KEY([role])
REFERENCES [dbo].[roles] ([role])
GO

ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_roles]
GO

