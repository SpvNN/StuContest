



![](/home/pavel/Documents/University/SprPo/StuContest/images/dbarch.png)

<h2>Data dictionary index</h2>
 <ul id="index">
 <li><strong>Database: </strong>StuContest</strong>
 <li><br/><strong>Tables</strong>
 <ul>
 <li><a href="#connects.contest_problem">connects.contest_problem</a></li>
 <li><a href="#connects.group_contest">connects.group_contest</a></li>
 <li><a href="#connects.problem_restriction">connects.problem_restriction</a></li>
 <li><a href="#contests.contests">contests.contests</a></li>
 <li><a href="#problems.problems">problems.problems</a></li>
 <li><a href="#problems.restrictions">problems.restrictions</a></li>
 <li><a href="#problems.solutions">problems.solutions</a></li>
 <li><a href="#problems.tests">problems.tests</a></li>
 <li><a href="#users.groups">users.groups</a></li>
 <li><a href="#users.roles">users.roles</a></li>
 <li><a href="#users.users">users.users</a></li>
 </ul>
 </li>
 </ul>
<table id="connects.contest_problem" class="table">
 <caption class="tab-name">
 <em>connects</em>.<strong>contest_problem</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>contest_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>problem_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>contest_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>contest_id</td>
 <td><a href="#contests.contests">contests.contests</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>problem_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>problem_id</td>
 <td><a href="#problems.problems">problems.problems</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="connects.group_contest" class="table">
 <caption class="tab-name">
 <em>connects</em>.<strong>group_contest</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>group_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>contest_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>contests_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>contest_id</td>
 <td><a href="#contests.contests">contests.contests</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>group_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>group_id</td>
 <td><a href="#users.groups">users.groups</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="connects.problem_restriction" class="table">
 <caption class="tab-name">
 <em>connects</em>.<strong>problem_restriction</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>problem_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>restriction_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>problem_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>problem_id</td>
 <td><a href="#problems.problems">problems.problems</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>restriction_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>restriction_id</td>
 <td><a href="#problems.restrictions">problems.restrictions</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="contests.contests" class="table">
 <caption class="tab-name">
 <em>contests</em>.<strong>contests</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>contests_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="problems.problems" class="table">
 <caption class="tab-name">
 <em>problems</em>.<strong>problems</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>conditions</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>solution</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>difficulty</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>problems_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>user_id</td>
 <td><a href="#users.users">users.users</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="problems.restrictions" class="table">
 <caption class="tab-name">
 <em>problems</em>.<strong>restrictions</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>type</td>
 <td class="data-type">smallint</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>restriction</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>restrictions_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="problems.solutions" class="table">
 <caption class="tab-name">
 <em>problems</em>.<strong>solutions</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>problem_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>solution</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>solution_status</td>
 <td class="data-type">smallint</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>solutions_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>problem_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>problem_id</td>
 <td><a href="#problems.problems">problems.problems</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>user_id</td>
 <td><a href="#users.users">users.users</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="problems.tests" class="table">
 <caption class="tab-name">
 <em>problems</em>.<strong>tests</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>problem_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>data_in</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>data_out</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>pos</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>type</td>
 <td class="data-type">smallint</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>problem_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>problem_id</td>
 <td><a href="#problems.problems">problems.problems</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="users.groups" class="table">
 <caption class="tab-name">
 <em>users</em>.<strong>groups</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>user_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>groups_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="users.roles" class="table">
 <caption class="tab-name">
 <em>users</em>.<strong>roles</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>user_id</td>
 <td class="data-type">integer</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>role</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>user_fk</td>
 <td class="value constr-type">FOREIGN KEY</td>
 <td>user_id</td>
 <td><a href="#users.users">users.users</a></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<table id="users.users" class="table">
 <caption class="tab-name">
 <em>users</em>.<strong>users</strong>
 <span class="type-label">Table</span>
 </caption>
 <thead>
 <tr>
 <th>Name</th>
 <th>Data type</th>
 <th>PK</th>
 <th>FK</th>
 <th>UQ</th>
 <th>Not null</th>
 <th>Default value</th>
 <th>Description</th>
 </tr>
 </thead>
 <tbody>
 <tr>
 <td>id</td>
 <td class="data-type">integer</td>
 <td class="bool-field">&#10003;</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>nick</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>password</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field">&#10003;</td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td>about</td>
 <td class="data-type">text</td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="bool-field"></td>
 <td class="value"></td>
 <td><p class="max-td-wth"><em></em></p></td>
 </tr>
 <tr>
 <td colspan="8" class="nested-tab-parent">
 <table class="nested-tab">
 <tr>
 <td class="title" colspan="6">Constraints</td>
 </tr>
 <tr>
 <td class="title">Name</td>
 <td class="title">Type</td>
 <td class="title">Column(s)</td>
 <td class="title">References</td>
 <td class="title">Expression</td>
 <td class="title">Description</td>
 </tr>
 <tr>
 <td>users_pk</td>
 <td class="value constr-type">PRIMARY KEY</td>
 <td>id</td>
 <td></td>
 <td class="value"></td>
 <td colspan="3"><p class="max-td-wth"><em></em></p></td>
 </tr>
 </table>
 </td>
 </tr>
 </tbody>
</table>

<div>
 <a href="#index" class="nav-link">↑ Index</a>
</div>
<footer>
Generated at <em>2023-04-14T08:49:08 </em> by <strong>pgModeler 1.0.2</strong><br/>
<a href="https://pgmodeler.io">PostgreSQL Database Modeler - https://pgmodeler.io</a><br/>
Copyright © 2006 - 2023 Raphael Araújo e Silva
</footer>
