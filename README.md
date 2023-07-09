## 소개

안녕하세요, 페이징은 어려운게 아닌데, 간단한 어드민 만들때마다 코드를 넣어줘야하고, 종류별로 하나씩 다르게 해야할땐 시간을 쏫는게 너무 아깝다고 생각했어요. 비록, 큰 기능은 아니지만 웹과 상관없이 자바 어플리케이션이라면 모두 사용가능한 페이지네이터 입니다.  

이 라이브러리는 빠른 페이징 처리를 위해 개발한 간단한 Offset 페이징 라이브러리이며, 실시간 데이터 처리를 위한 커서 페이징에대한 기능은 포함되어있지 않습니다. 기능개발을 하면서 빠른 기능개발을 위해 사용하기 위한 페이지네이터 이며, 관리자페이지 또는 게시판 에서 사용하길 추천 드립니다. JVM이 있다면 어디서든 사용가능하며, Spring, Swing, javafx ..등등 어디서든 사용가능합니다 .

사용시 불편한사항이나 개선사항이 필요하다면 tnine923@gmail.com 으로 언제든 환영입니다 : )

*즐거운 개발을 응원드리며, star 한번씩 부탁 드리겠습니다.*

## [Usage as English](https://github.com/kimchi-dev/offset-paginator/blob/main/README_EN.md)

## 의존성 추가

**Apache Maven**

```xml
<dependency>
  <groupId>kr.devis.util.offsetpaginator</groupId>
  <artifactId>offset-paginator</artifactId>
  <version>1.0.0</version>
</dependency>
```

**Gradle Groovy DSL**

```groovy
implementation 'kr.devis.util.offsetpaginator:offset-paginator:1.0.4'
```

**Gradle Kotlin DSL**

```kotlin
implementation("kr.devis.util.offsetpaginator:offset-paginator:1.0.4")
```

**사용하시려는 빌드툴이 없거나 Jar파일이 필요하시다면, [메이븐 저장소](https://search.maven.org/artifact/kr.devis.util.offsetpaginator/offset-paginator/1.0.0/jar) 에서 확인할 수 있습니다.**

**jar**파일 다운로드는 메이븐 저장소의 이미지의 버튼 을 통해 이용가능합니다.

![download-jar](image/maven central.png?raw=true)


<br>
<hr>

## 1. 사용법

![explain](image/paginated3.png?raw=true)

사용법을 소개하기 이전에 이 문서에서 자주 언급 될 `A` 와 `B`에 대해 설명드리겠습니다.
이미지를 보시는 바와같이 `A`는 한 페이지에 노출될 페이징 블럭 개수를 의미합니다. 또한 `B`는 한페이지에서 노출될 컨텐츠의 개수를 의미합니다.

<br>
<hr>

### 1-1. 기본값을 이용한 선언

```java
//선언
OffsetPaginator paginator = new OffsetPaginator(boardList.size(), currentPage);

//사용할 페이징 옵션 선택 및 결과
PaginatedObject result = paginator.elastic()
        .build()
        .paginate();
```
최종적으로 `build()`메서드에서 연산을 진행하며, `paginate()`메서드로 결과를 반환받습니다. `OffsetPaginator(A,B)`생성자를 사용 시 기본값으로 위의 이미지의 `A: 10`,`B: 10` 및 `MYSQL`의 데이터베이스로 세팅 됩니다.

### 1-2. 커스텀한 세팅을 위한 선언

```java
//선언
OffsetPaginator paginator = new OffsetPaginator();

//사용할 페이징 옵션 선택
paginator.init(boardList.size(), 8, 3, currentPage, PaginatorConstant.MYSQL_PAGING);

//결과
PaginatedObject result = paginator.elastic().build().paginate();
```
커스텀한 페이징을 위해 위와같이 기본생성자를 사용하여, 한 페이지에서 보여줄, 페이징 버튼 블럭의 개수 또는 컨텐츠의 개수 및 사용하는 데이터 베이스를 수정하고싶다면 `init()` 옵션을 통해 사용가능합니다.

*참고: `1-2`의 사용법은 `1.0.4`버전부터 사용이 가능합니다.*

<br>
<hr>

### 1-3. 데이터 베이스 옵션


| 사용중인 데이터베이스 선택                | Offset 페이징으로 적용될 항목 |
| ----------------------------------- | ------------------------- |
| PaginatorConstant.ORACLE_PAGING     |  오라클 데이터베이스         |
| PaginatorConstant.MYSQL_PAGING      | MySQL 데이터 베이스         |
| PaginatorConstant.POSTGRESQL_PAGING | PostgreSQL 데이터 베이스    |

<br>
<hr>

### 1-4-1. 이전 또는 다음으로 이동

`1.0.4`부터는 `if`와 같은 분기문 없이 `move()`메서드를 이용해 좀 더 적은 코드로 사용이 가능합니다.

```java
boolean clickedPre = false; //사용자가 이전버튼을 누른 경우
boolean clickedNext = false; // 사용자가 다음버튼을 누른경우

PaginatedObject result = paginator.elastic()
        .move(clickedPre, clickedNext)
        .build()
        .paginate();
```

<br>
<hr>

### 1-5. 종합적인 사용

*위의 사용법을 종합하면 아래와 같이 사용할 수 있습니다.*

**기본값을 이용한 사용**
```java
PaginatedObject result = new OffsetPaginator(boardList.size(), currentPage)
        .elastic()
        .move(isPre, isNext)
        .build()
        .paginate();
```

**커스텀값을 이용한 사용**
```java
OffsetPaginator paginator = new OffsetPaginator();
paginator.init(boardList.size(), 8, 3, currentPage, PaginatorConstant.ORACLE_PAGING);

PaginatedObject result = paginator.elastic()
        .move(isPre, isNext)
        .build()
        .paginate();
```

<br>
<hr>

### 1-6-1. 엘라스틱 페이징 옵션 

페이지네이터의 적용되는 옵션은 크게 두가지 `elastic()`과 `fixed()` 입니다.

먼저 `1-5`에서 사용하였던 `elastic()` **:탄력적인** 옵션을 적용하는 경우 아래처럼 페이징 이동이 적용됩니다.


![elasetic normal](image/elastic_normal.gif?raw=true)

엘라스틱 페이징은 페이징의 스텝(단계)안에서 탄력적으로 움직입니다. 예를 들어 페이지가 **21**개이면서 한페이지에서 보여줄 페이지의 블럭이 **8**이라면 아래와 같이 계산됩니다.


| 단계 (Step)               | 페이징 블럭들 |
| ----------------------------------- | ------------------------- |
| 1 | [ 1 ][ 2 ][ 3 ][ 4 ][ 5 ][ 6 ][ 7 ][ 8 ] |
| 2 | [ 9 ][ 10 ][ 11 ][ 12 ][ 13 ][ 14 ][ 15 ][ 16 ] |
| 3 | [ 17 ][ 18 ][ 19 ][ 20 ][ 21 ] |


단계 이동은 아래의 이미지처럼 이전 또는 다음 단계로 이동이 가능합니다.

![elasetic move](image/elastic_move.gif?raw=true)

`elastic()` 페이징 옵션은 다음으로 갈 시 다음단계의 **맨처음** 페이지로 이동됩니다. 따라서 이전단계로 이동 시 이전 단계의 맨 **마지막** 페이지로 이동합니다.

### 1-6-2. 픽스드 페이징 옵션

`fixed()` **:고정된** 옵션을 적용하는 경우 아래처럼 고정된 페이징 이동 적용됩니다.


![fixed normal](image/fixed_normal.gif?raw=true)

```java
paginator.fixed()
        .build()
        .paginate();
```

위의 예시는 한페이지에서 노출되는 페이징블럭 **A**가 **7**이며, 중간값(pivot)이 4이므로 페이징블럭 **4까지는** 이동 하여도 최소한의 노출을위해 `elastic()`과 동일하게 동작합니다. 하지만, **5**부터는 제일 처음에보여줄 블럭인 [2]를 시작으로 출력되며 이후에도 현재페이지가 고정된 페이징을 지원합니다.

픽스드페이징 옵션의 단계이동은 **현재 페이지** + **A** 의 연산으로 적용됩니다.

![fixed move](image/fixed_move.gif?raw=true)

위와같이 **15**개의 페이징 블럭이 존재할 경우, `1 -> 8 -> 15` 순서로 `A: 7`의 단계를 이동합니다. **픽스드페이징**은 **엘라스틱페이징**과 다르게 다음단계로 이동시 `+ A`를 적용하며, 만약 이동하려는 페이지가 최종 페이징블럭의 크기보다 크다면, 최종페이징 블럭으로 이동합니다. 

예를들어 `A:5`이면서 최종 페이지블럭이 **17**이라면 `1 -> 6 -> 11 -> 16 -> 21`의 순서로 가야하지만 최종 페이지블럭인 **17**보다 이동하려는 페이징블럭인 **21**이 더 크므로 최종페이지블럭인 **17**로 이동하게 되기 때문에 `1 -> 6 -> 11 -> 16 -> 17`의 단계 이동이 적용됩니다.

<br>
<hr>

### 1-7. HTML과 CSS의 지원

이 라이브러리는 간단한 하며 빠른 Offset 페이징을 위한 라이브러리이며, 보다 손쉬운 `HTML` 및 `CSS`를 지원합니다. 이기능은 `PageMaker`를 통해 지원됩니다. 간단한 설명을 위해 아래와같이 페이징연산이 완료된 `result` 객체가 있다는 가정하에 설명을 진행하겠습니다.

```java
PaginatedObject result = new OffsetPaginator(boardList.size(), currentPage)
                .elastic()
                .move(isPre, isNext)
                .build()
                .paginate();
```

HTML과 CSS없이 직접 다루고 싶으시다면 위의`result` 객체를 이용하여 아래와 같이 연산된 값을 적용할 수 있습니다.

```java
int contentsPerPage = result.getContentsPerPage(); //B, 한페이지에 보여줄 컨텐츠 개수
int currentPage = result.getCurrentPage(); // 현재 페이지
int startPage = result.getStartPage(); // 현재단계의 시작페이지
int endPage = result.getEndPage(); // 현재단계의 마지막페이지
boolean hasNextPage = result.hasNextPage(); // 다음페이지 존재 유무
        
int startIndex = result.getStartIndex(); // DB에 따른 시작 index
int endIndex = result.getEndIndex();  //DB에 따른 종료 index
```

위의 `index`는 데이터 베이스마다 적용되는 쿼리에 따라 의미가 다르기 때문에 `pagingLog`를 통해 추천되는 쿼리로 사용할 수 있습니다. 이 의미는 `paginator.getPagingLog()` 메서드의 호출로 확인이 가능합니다.

```java
OffsetPaginator paginator = new OffsetPaginator(boardList.size(), currentPage);
paginator.elastic()
        .move(isPre, isNext)
        .build()
        .paginate();

System.out.println(paginator.getPagingLog());
```

위의 출력 결과로 index의 사용방식을 적용할 수 있습니다.
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
```

`startIndex`와 `endIndex` 변수는 위 출력결과중 맨 마지막 `==> LIMIT 2021151,    11`처럼 사용이가능 합니다.


**HTML 및 CSS**

HTML 과 CSS를 얻는 방법은 매우 간단합니다. 
```java
PageMaker maker = new PageMaker(result, "/board/list")
        
maker.html().get(); // html 
maker.html().css().get(); // html + css
```
`PageMaker()` 생성자의 첫번째 인자는 페이징 연산된 PagenatedObject객체로, 두번째 인자는 해당 컨텐츠 리스트를 사용할 `End point`로서 입력합니다.
위의 `get()` 메서드는 html 및 css 가 적용된 `TAG`를 String의 형태로 반환합니다. 따라서 아래처럼 반환된 문자열을 출력하여 결과를 얻어 사용할 수 있습니다.

```java
String htmlAndCss = maker.html().css().get();
System.out.println(htmlAndCss);
```

출력 결과: 
```terminal
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

문자열로 얻을 수 있지만 적용될 샘플 페이지를 다운로드 할 수도 있습니다.

`get()`메서드 대신 `download(path)`메서드로 샘플 다운로드가 가능합니다.

```java
maker.html().css().download("c:/pagination/test");
```
확장자를 입력하지 않을 경우 `c:/pagination/test` 경로에`result.html`이라는 파일로 자동생성 됩니다. 만약 샘플파일의 파일명을 임의로 설정 하고 싶으시다면, `c:/pagination/test/temp.html`의 형식으로 다운로드가 가능합니다.

**주의할 점: 만약 `c:/pagination/test`라는 경로 로 지정 하였지만, `c:/pagination`의 경로에 `test`라는 디렉토리가 아닌 파일이 존재할 경우 예외를 던집니다.**

`PagiMaker`의 경우 옵션을 추가하여, 다음과 같이 이동버튼명을 수정하거나 특정상황에 안보이도록 적용할 수있습니다. 

![init](image/paginated1.png?raw=true)

```java
String generated = new PageMaker(result, "/board/list")
        .setMoveButtonName("이전", "다음")
        .html().css().get();
```

예를들어 버튼명을 `이전` 및 `다음`으로 변경하고 싶은경우 위의 `setMoveButtonName()`메서드를 통해 변경할 수있습니다. 기본값은 `Pre`, `Next`입니다. 

또한 `exposeDisabled()` 메서드를 통해 아래와 같이 다음의 단계로 이동할수 없는경우에도 단계이동 버튼을 보여줄 수 있습니다. 하지만 실제 이동은 불가합니다.

![expose-disabled1](image/exposeDisabled.gif?raw=true)
```java
String generated = new PageMaker(result, "/board/list")
        .setMoveButtonName("이전", "다음")
        .exposeDisabled()
        .html().css().get();
```

### 1.8 페이징의 연산결과 출력

`OffsetPaginator`를 통해 페이지네이션 결과를 출력하여 확인 할 수 있습니다. 사용법은 간단합니다. `paginator.getPagingLog();`를 통해 페이지네이션 로그를 문자열로 반환받을수 있습니다.

```java
OffsetPaginator paginator = new OffsetPaginator(21059430, 183742);
        paginator.init(21059430, 11, 17, 183742, PaginatorConstant.POSTGRESQL_PAGING);
        
paginator.elastic()
        .move(isPre, isNext)
        .build()
        .paginate();

String log = paginator.getPagingLog();
System.out.println(log);
```

출력결과:

```terminal
----------- Welcome To Kimchi Paginator -----------
[Pre][183734][183735][183736][183737][183738][183739][183740][183741] 183742 [183743][183744][Next]
                                                                      ↑

[Total Page Count]      : [1238790]
[Total Step Count]      : [112618]
[Currently Step]        : [16704]
[Currently Page Number] : [183742]
[Result time] : [0.003] sec
You can paginate with PostgreSQL DataBase's SQL that s like either following Queries.
 ==> OFFSET 3123597 ROWS FETCH LIMIT    17
---------------------------------------------------
```