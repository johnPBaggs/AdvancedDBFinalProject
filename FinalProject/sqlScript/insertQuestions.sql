INSERT INTO [dbo].[question](
	[questionId],
	[question],
	[optionA],
	[optionB],
	[optionC],
	[optionD],
	[answer] ,
	[courseId])
     VALUES
           (next value for [dbo].[questionIds]
           ,'question'
		   , 'optionA'
		   ,'optionB'
		   ,'optionC'
		   ,'optionD'
		   ,'answer'
		   ,courseId)