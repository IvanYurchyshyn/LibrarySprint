select *
from users;

select distinct id
from users;

select count(*)
from book_borrow
where is_returned = 0;

select *
from book_categories;


select *
from books
where name = 'Clean Code'
order by isbn desc
LIMIT 1;
select name
from book_categories
where id = 10;

select bc.name
from book_borrow
         join books b on book_borrow.book_id = b.id
         join book_categories bc on b.book_category_id = bc.id
group by bc.name
order by count(*) desc
LIMIT 1;

select *
from books;

select name from books
where name = 'Clean Code';

select email,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where email= 'student58@library' and name= 'Lost Java Book 3'
order by 3 desc;