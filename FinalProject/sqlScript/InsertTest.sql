SELECT NEXT VALUE FOR [TestDB].[dbo].[testIds] as @testId

INSERT INTO [TestDB].[dbo].[test] ([testId]
      ,[testName]
      ,[time]
      ,[courseId])
	  VALUES(
	  @testId
	  ,'test1'
	  ,20
	  ,1001)

INSERT INTO [TestDB].[dbo].[testQuestions]([testId]
		,[questionId])
		VALUES(
		testId
		,questionId)
GO