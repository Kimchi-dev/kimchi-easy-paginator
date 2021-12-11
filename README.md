*Introduction*

안녕하세요, 저는 Kimchi-dev입니다.
페이징은 어려운게 아닌데, 간단한 어드민 만들때마다 코드를 넣어줘야하고, 종류별로 하나씩 다르게 해야할땐 시간을 쏫는게 너무 아깝다고 생각했어요.

비록, 큰 기능은 아니지만 웹과 상관없이 자바 어플리케이션이라면 모두 사용가능한 페이지네이터 입니다.  
부족한 영어지만 모두가 볼수 있도록 영어로 작성 하겠습니다! 코드만 보셔도 쉽게 사용 하실수 있습니다.

사용시 불편한사항이나 개선사항이 필요하다면 tnine923@gmail.com 으로 언제든 환영입니다 : )

### Dependencies

**Apache Maven**

```xml
<dependency>
  <groupId>io.github.kimchi-dev</groupId>
  <artifactId>kimchi-easy-paginator</artifactId>
  <version>1.0.3</version>
</dependency>
```

**Gradle Groovy DSL**

```groovy
implementation 'io.github.kimchi-dev:kimchi-easy-paginator:1.0.3'
```

**Gradle Kotlin DSL**

```kotlin
implementation("io.github.kimchi-dev:kimchi-easy-paginator:1.0.3")
```

### There are no dependencies that if you want, note [Maven Central](https://search.maven.org/artifact/io.github.kimchi-dev/kimchi-easy-paginator/1.0.2/jar)

<br/>
<hr>

<br/>
<br/>


![init](image/pagination.png?raw=true)

# give it to me, if you bothered!

## At that time, you only need to choose 3 things!

1. number of page block size per page.
2. number of contents per page.
3. your database. (Ex. PaginatorConstant.MYSQL_PAGING)


## PaginatorConstant

| Select Your DataBase                | descriptions              |
| ----------------------------------- | ------------------------- |
| PaginatorConstant.ORACLE_PAGING     | Paginating for Oracle DB  |
| PaginatorConstant.MYSQL_PAGING      | Paginating for MySql      |
| PaginatorConstant.POSTGRESQL_PAGING | Paginating for PostgreSQL |


## Seeak peek (note Example)

<br>

### When fixed mode and other page.
<br>



![elasetic normal](image/elastic_normal.gif?raw=true)
```java
paginator.elastic().build().paginate();
```

<hr>
<br>

### When elastic mode and Move.
<br>




![elasetic move](image/elastic_move.gif?raw=true)
```java
paginator.elastic();

if(pre) {
    paginator.pre();    
} else if(next) {
    paginator.next();    
}

paginator.build().paginate();
```
<hr>
<br>

### When fixed mode and other page.
<br>


![fixed normal](image/fixed_normal.gif?raw=true)
```java
paginator.fixed().build().paginate();
```
<hr>
<br>

### When fixed mode and Move.
<br>


![fixed move](image/fixed_move.gif?raw=true)
```java
paginator.fixed();

if(pre) {
    paginator.pre();    
} else if(next) {
    paginator.next();    
}

paginator.build().paginate();
```
<hr>
<br>

### When set the custom button name.
<br>


![expose disabed button](image/button_name.gif?raw=true)
```java
PagingMaker maker = new PagingMaker(result, "/boards");
maker.setMoveButtonName("이전", "다음");
```
<hr>
<br>

### When expose disabled button. (add param)
<br>


![expose disabed button](image/exposeDisabled.gif?raw=true)
```java
PagingMaker maker = new PagingMaker(result, "/boards", true);
maker.setMoveButtonName("이전", "다음");
```

## Usage


There are 3V !

Very Simple, Very Convenience, Very delicious XD.

Java Application ? Spring ? Etc ? It doesn't matter if in JVM

![init](image/paginated3.png?raw=true)

*Note: You can control these list size.*
```java
KimchiPaginator paginator = new KimchiPaginator();
paginator.init(boardList.size(), {A}, {B},{Select DataBase})
```

### Do it !

```java
@Service
@RequiredConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;
    
    @Transactional(readOnly = true) // matching for both total board size and actual lookuped board size.
    public ResponseEntity<List<Board>> getBoardList(int currentPage) {
        
        //1. Create Paginator 
        KimchiPaginator paginator = new KimchiPaginator();
      	int totalSize = boardRepository.getTotalBoardSize();
        
      	//2. Initialize Paginator
        paginator.init(totalSize, 8, 3, PaginatorConstant.POSTGRESQL_PAGING);
        
        //3. Using Result (End)
        PaginatedObject result = paginator.elastic()
                .build() //actual Calculating
                .paginate(); // paginate with actual calculated stuff
        
        List<Board> boardList = boardRepository.findBoardList(result.getStartIndex(), result.getEndIndex());
        
        ResultBoardDto response = ResultBoardDto.builder()
                .boardList(boardList)
                .pagination(result)
                .build()
        ;
        
        return ResponseEntity.ok(response);
    }
}
```


### Paging Option

**If you want to changing other paging mode ?**
* There are two Pagination mode !
```java
paginator.elastic().build().paginate(); // [1][2][3][4][5][6][7] 8 [9] It is not to fixed !
paginator.fixed().build().paginate();   // [4][5][6][7] 8 [9][10][11][12] If currently page isn't positioning at side, that Always fixed with center !
```

**If you bother to appying both move previous and next ?**

* There are two move functions !

```java
boolean clickPre = true; //when they clicked previous button
boolean clickNext = false; //when they clicked next button

if(clickPre) {
    paginator.pre();
} else if(clickNext) {
    paginator.next();
}

PaginatedObject result = paginator.elastic().build().paginate(); // or paginator.fixed();

//There are essential values that, which for custom pagination !
boolean ableToPreStep = result.isAbleToPreStep(); 
boolean ableToNextStep = result.isAbleToNextStep();
boolean hasNextPage = result.hasNextPage();
int currentlyPage = result.getCurrentPage();
int startPage = result.getStartPage();
int endPage = result.getEndPage();

/* for DataBase index */
int startIndex = result.getStartIndex();
int endIndex = result.getEndIndex();
    
```


### You can get a log for pagination !

* In Java

```java
KimchiPaginator paginator = new KimchiPaginator();

int currentPage = page; //9

paginator.init(totalBoardList.size(), 8, 3, currentPage, PaginatorConstant.MYSQL_PAGING);
PaginatedObject result = paginator.elastic()
        .build()
        .paginate();
PaginatedObject result = paginator.elastic().build().paginate();

String pagingLog = paginator.getPagingLog();
System.out.println(pagingLog);
```

* In terminal

```terminal
----------- Welcome To Kimchi Paginator -----------
[Pre] 9 [10][11][12][13][14][15][16][Next]
      ↑

[Total Page Count]      : [   42]
[Total Step Count]      : [    6]
[Currently Step]        : [    2]
[Currently Page Number] : [    9]
[Result time] : [0.002] sec
You can paginate with MySQL DataBase's SQL that like following Query.
==> LIMIT    64,     8
---------------------------------------------------
```


### That's not All !

Create simple application and just setting first !

```java
@RestController
public class BoardController {
    
    @GetMapping("/board/list")
    public ResponseEntity<List<Board>> lookUpBoardList(int currentPage) throws Exception {
        KimchiPaginator paginator = new KimchiPaginator();

        int currentPage = currentPage; //127764

			//totalBoardList.size() = 7893929
        paginator.init(totalBoardList.size(), 11, 17, currentPage, PaginatorConstant.MYSQL_PAGING);
        PaginatedObject result = paginator.elastic()
            .build()
            .paginate();
        
        PaginatedObject result = paginator.elastic().build().paginate();

        ...
    }
}
```


### Sample Html Download ? automatically create html element ? Can Anithing.

**I prepared All function of pagination !**


#### Download for sample html file.

```java
...
//Maker setting.
PagingMaker maker = new PagingMaker(result, "/board/list"); // must be current end point;
maker.setMoveButtonName("이전", "다음"); //if you didn't set the button name, it will be automatically set the button name as "Pre" and "Next"

// noting paginated element.
maker.generate().download("/Users/Kimchi-dev/Documents/pagination/sample.html");
// only html of pagination elements. 
maker.html().generate().download("/Users/Kimchi-dev/Documents/pagination/sample.html");
// html + css of pagination elements.
maker.html().withCss().generate().download("/Users/Kimchi-dev/Documents/pagination/sample.html");

```


![init](image/paginated1.png?raw=true)

[Fig.1] generated sample html


#### Expose move buttons,when even can't move for next.

```java 
...

PagingMaker maker = new PagingMaker(result, "/board/list", true); //last parameter meaning of "expose move button at all situation.
...

```


![init](image/paginated2.png?raw=true)

[Fig.2] disabled next button.

```terminal
----------- Welcome To Kimchi Paginator -----------
[Pre][464344][464345][464346][464347][464348] 464349 
                                              ↑

[Total Page Count]      : [464349]
[Total Step Count]      : [42214]
[Currently Step]        : [42214]
[Currently Page Number] : [464349]
[Result time] : [0.007] sec
You can paginate with MySQL DataBase's SQL that like following Query.
==> LIMIT 5107828,    11
---------------------------------------------------
```


### Getting for a paginated html elements

```java
PagingMaker maker = new PagingMaker(result, "/board/list", true);
maker.setMoveButtonName("<-~", "~->");

String onlyHtmlElement = maker.html().get(); //only html for custom css
String paginationElement = maker.html().withCss().get(); //html + css

String pagingLog = paginator.getPagingLog();
System.out.println(pagingLog);
System.out.println(paginationElement);

```

```terminal
----------- Welcome To Kimchi Paginator -----------
[Pre][183734][183735][183736][183737][183738][183739][183740][183741] 183742 [183743][183744][Next]
                                                                      ↑

[Total Page Count]      : [464349]
[Total Step Count]      : [42214]
[Currently Step]        : [16704]
[Currently Page Number] : [183742]
[Result time] : [0.007] sec
You can paginate with MySQL DataBase's SQL that like following Query.
==> LIMIT 2021151,    11
---------------------------------------------------

<div class="pagination-container">
	<ul class="pagination">
		<li class="page-item">
			<a class="pre-button" href="/board/list?currentPage=183742&pre=true"><-~</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183734">183734</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183735">183735</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183736">183736</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183737">183737</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183738">183738</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183739">183739</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183740">183740</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183741">183741</a>
		</li>
		<li class="page-item active disabled"><a class="page-link">183742</a></li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183743">183743</a>
		</li>
		<li class="page-item" aria-current="page">
			<a class="page-link" href="/board/list?currentPage=183744">183744</a>
		</li>
		<li class="page-item">
			<a class="next-button" href="/board/list?currentPage=183742&next=true">~-></a>
		</li>
	</ul>
</div>

<style>
/* Pagination area */
.pagination-container {
    padding: 0 0;
    margin: 30px auto;
    width: 1024px;
    height: 40px;
    justify-content: center;
    display: flex;
}
ul.pagination {
    display: inline-block;
    padding: 5px 0;
    height: 30px;
    margin: 0 auto;
}
ul.pagination li {
    list-style-type: none;
    float: left;
}
.pagination li {
    margin: 0 6px;
}
li.page-item {
    font-size: 1rem;
}
.pagination .active .page-link {
    background-color: #3b64e8;
    border-radius: 5px;
    color: white;
    cursor: default;
}
.pagination .disabled span{
    color: gray;
}
:where(li.page-item a.page-link, a.pre-button, a.next-button):hover {
    background-color: #3b64e8;
    color: white;
    transition: .6s;
    border-radius: 5px;
    cursor: pointer;
}
:where(li.page-item a.page-link, a.pre-button,a.next-button) {
    background-color: white;
    color: black;
    text-decoration: none;
    transition: .6s;
    border-radius: 5px;
    padding: 2px 10px;
    cursor: pointer;
}
</style>
```

### Have your self !