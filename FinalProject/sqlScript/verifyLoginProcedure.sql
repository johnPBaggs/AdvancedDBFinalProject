-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Keerthi, John>
-- Create date: <10/29/2016>
-- Description:	<procedure for verifying login information>
-- =============================================
CREATE PROCEDURE dbo.verifyLogin (
	-- Add the parameters for the stored procedure here
	@id int OUT,
	@userName nvarchar(50), 
	@password nvarchar(50),
	@role nvarchar(50))
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT @id = [id]
	FROM [TestDB].[dbo].[user]
	WHERE [userName] = @userName
	AND [password] = @password
	AND [role] = @role
END
GO
