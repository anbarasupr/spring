ONE to MANY:
An instructor can have multiple courses

MANY to ONE:
Many courses can have one instructor.
	(Inverse / opposite of one to many)

Requirement:	
If u delete an instructor, DO NOT delete the courses.
if u delete a course, DO NOT delete the instructor
	(DO NOT apply cascade delete operation)