<html>
<head>
    <style>
        table {
            border-collapse: collapse;
        }

        .plus {
            border: 1px solid black;
            padding-left: 4px;
            padding-right: 4px;
        }

        .minus {
            border: 1px solid black;
            padding-left: 6px;
            padding-right: 6px;
        }

        .parentContainer {
            padding-left: 0px;
            padding-right: 2px;
            padding-top: 2px;
            padding-bottom: 2px;
        }

        .treeLineContainer {
            padding: 0px;
        }

        .childTop {
            float: top;
            height: 11px;
            width: 100% t;
            border-left: 1px;
            border-right: 0px;
            border-top: 0px;
            border-bottom: 1px;
            border-style: solid;
            border-color: black;
        }

        .childBottom {
            position: absolute;
            top: 11px;
            bottom: 0px;
            width: 100%;
            border-left: 1px;
            border-right: 0px;
            border-top: 0px;
            border-bottom: 0px;
            border-style: solid;
            border-color: black;
        }

        .childRight {
            float: right;
            width: 50%;
            height: 100%;
            position: relative;
        }

        .childLeft {
            float: left;
            width: 50%;
            height: 100%;
        }

        .grid {
            display: grid;
        }

        .fill {
            width: 100%;
            display: inline-block;
            height: 100%;
            padding: 0px;
            vertical-align: top;
        }
    </style>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
    <script type="text/javascript">
        function expand($parent, $clickable, href) {
            $.ajax(href).done(function (file) {
                for (var i = 0; i < file.children.length; i++) {
                    var childFile = file.children[i];
                    var $childRow = $("<tr></tr>").addClass("child");
                    $parent.append($childRow);
                    var $treeLineCell = $("<td></td>").addClass("treeLineContainer");
                    $childRow.append($treeLineCell);
                    var $fillGridDiv = $("<div></div>").addClass("fill grid");
                    $treeLineCell.append($fillGridDiv);
                    var $rightDiv = $("<div></div>").addClass("childRight");
                    $fillGridDiv.append($rightDiv);
                    var $topDiv = $("<div></div>").addClass("childTop");
                    $rightDiv.append($topDiv);
                    if (i < (file.children.length - 1)) {
                        var $bottomDiv = $("<div></div>").addClass("childBottom");
                        $rightDiv.append($bottomDiv);
                    }
                    var $childCell = $("<td></td>").addClass("parentContainer");
                    $childRow.append($childCell);
                    if (childFile.isDirectory) {
                        createDirectoryView(childFile, $childCell);
                    }
                    else {
                        createFileView(childFile, $childCell);
                    }
                }
            });
            var $clickParent = $clickable.parent();
            $clickable.remove();
            var $clickCell = $("<td>-</td>").addClass("minus");
            $clickParent.prepend($clickCell);
            $clickCell.click(function () {
                collapse($parent, $clickCell, href);
            });
        }

        function collapse($parent, $clickable, href) {
            $parent.find(".child").remove();
            var $clickParent = $clickable.parent();
            $clickable.remove();
            var $clickCell = $("<td>+</td>").addClass("plus");
            $clickParent.prepend($clickCell);
            $clickCell.click(function () {
                expand($parent, $clickCell, href);
            });
        }

        function createFileView(file, $parent) {
            var $anchor = $("<a>" + file.name + "</a>").attr("href", file.href).attr("target", "_blank");
            $parent.append($anchor);
            return $anchor;
        }

        function createDirectoryView(file, $parent) {
            var $table = $("<table></table>");
            $parent.append($table);
            var $row = $("<tr></tr>");
            $table.append($row);
            var $clickCell = $("<td>+</td>").addClass("plus");
            $row.append($clickCell);
            var $nameCell = $("<td>" + file.name + "</td>");
            $row.append($nameCell);
            $clickCell.click(function () {
                expand($table, $clickCell, file.href);
            });
            return [$table, $clickCell];
        }

        $(document).ready(
                function () {
                    $.ajax("rest/file").done(function (file) {
                        var $treeDiv = $('#treeDiv');
                        var tuple = createDirectoryView(file, $treeDiv);
                        expand(tuple[0], tuple[1], file.href);
                    })
                }
        )
    </script>
</head>
<body>
<h2>Filesystem Browser</h2>
<div id="treeDiv"></div>
</body>
</html>
