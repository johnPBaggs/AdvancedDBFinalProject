-- ================================================
-- Template generated from Template Explorer using:
-- Create Trigger (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- See additional Create Trigger templates for more
-- examples of different Trigger statements.
--
-- This block of comments will not be included in
-- the definition of the function.
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
CREATE TRIGGER [dbo].gradeTest 
   ON  [dbo].[testStagging] 
   AFTER UPDATE
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	If (SELECT completed FROM INSERTED) = 1
	BEGIN
		DECLARE @sId AS INT
		DECLARE @correctAnswers AS INT
		DECLARE @tId AS INT
		DECLARE @cId AS INT
		DECLARE @grade AS NCHAR(10)
		
		SELECT @sId = studentId, @tId = testId FROM INSERTED

		SELECT @cId = courseId FROM [TestDB].[dbo].[test] WHERE [testId] = @tId 
		
		SELECT @correctAnswers = COUNT(*)
			FROM [TestDB].[dbo].question q, [TestDB].[dbo].answers a
			WHERE q.questionId = a.questionId
			AND a.studentId = @sId
			AND a.answer = q.answer 
		
		SELECT @grade = grade 
			FROM [TestDB].[dbo].[pointsToGrade] 
			WHERE points = @correctAnswers

		INSERT INTO [TestDB].[dbo].[grade]([studentId]
      ,[testId]
      ,[courseId]
      ,[grade])
	  VALUES(@sId
			,@tId
			,@cId
			,@grade)

		DELETE [TestDB].[dbo].[answers] WHERE [testId] = @tId AND [studentId] = @sId

		DELETE [TestDB].[dbo].[testStagging] WHERE [testId] = @tId AND [studentId] = @sId
	END
END
GO
