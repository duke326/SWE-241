-- allow new students to enroll into the program,
insert into students values(default, "duke");
-- new courses to be introduced,
insert into courses values(default, "","Monday","7:00 PM");

-- students to enroll in courses,
insert into courses_has_students values(2,1);

-- querying to see which students are in each course, 
select students_id,s.name 
from courses_has_students cs join students s on cs.students_id=s.id 
where courses_id=1;

-- querying to see which courses each student is in,
select courses_id, c.name
from courses_has_students cs join courses c on cs.courses_id=c.id 
where students_id=1;

-- querying to see which courses and what times each course is for a given student on a given day of the week.
select courses_id, c.name, c.start_time
from courses_has_students cs join courses c on cs.courses_id=c.id 
where students_id=1 and c.day="Mon"; 
