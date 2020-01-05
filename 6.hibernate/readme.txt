
Database Concepts:
Primary Key:
identify a unique row in a table

Foreign key:
Link tables together
a field in one table that refers to primary key in another table


Cascade:
We can cascade operations
Apply the same operation to related entities
Ex: If we save instructor, then it does the same operation on instructor_detail
Defaultly, there is no cascade will apply. if we want, then we have to specify.

Cascade delete:
It depends on use case

Should we do cascade delete here?
A course can have multiple students and a student can have multiple courses - many to many mapping
In such case, cascade delete is not good way. If we delete a student, should we delete a course - no way.
So cascade delete is not applicable here

As a developer, we have fine grain control on cascade opetaions.

Fetch Types:
Eager vs Lazy loading

Eager will retrieve everything
Lazy will retrieve on request



Uni directional vs Bi-directional
Uni directional  - You load the instructor, and from there you can access the instructor_detail
Bi-directional -  You can access instructor and instructor_detail in both objects



Entity Life cycle:

Detach - If entity is detached, it is not associated with a hibernate session
Merge - If instance is detached from session, then merge will reattach to the hibernate session
Persist - Transitions new instances to managed state. Next flush/commit will save in db.
Remove - Transitions managed entity to be removed. Next flush/commit will delete from db.
Refresh - Reload/Synch object with data from db. prevents stale data.
  
