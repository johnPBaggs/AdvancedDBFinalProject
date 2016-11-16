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
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE getStudentGrades 
	-- Add the parameters for the stored procedure here
	@studentId int, 
	@courseId int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT t.[testName]
	 ,g.[grade]
  FROM [TestDB].[dbo].[grade] g, [TestDB].[dbo].[test] t
  WHERE t.[testId] = g.testId
  AND g.[studentId] = @studentId
  AND g.courseId = @courseId
END
GO
