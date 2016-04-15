<html>
<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
    <script type="text/javascript">
        function expand(listItem, href) {
            $.ajax(href).done(function (file) {
                var subList = document.createElement("ul")
                listItem.appendChild(subList)
                for (childFile of file.children) {
                    createListItem(childFile, subList);
                }
            });
        }
        function collapse(listItem) {
            var lists = listItem.getElementsByTagName("ul");
//            listItem.removeChild(lists[0]);
            for (var i = 0; i < lists.length; i++) {
                var list = lists[i];
//            for(var list of lists) {
                listItem.removeChild(list);
            }
        }
        function createListItem(file, list) {
            var listItem = document.createElement("li")
            var span = document.createElement("span")
            span.setAttribute("class", "Collapsable")
            listItem.setAttribute("href", file.href)
            listItem.appendChild(span)
            var textNode = document.createTextNode(file.name)
            span.appendChild(textNode)
            list.appendChild(listItem)
            /*
             var id = listItem.getElementsByTagName("span")[0].getAttribute("id");
             var name = listItem.getElementByTagName("span")[0].innerText
             */
            span.onclick = function () {
                lists = listItem.getElementsByTagName("ul")
                if (lists.length < 1) {
                    expand(listItem, file.href);
                }
                else {
                    collapse(listItem)
                }
            };
            return listItem;
        }
        function getSubList(listItem) {
            var list
            lists = listItem.getElementsByTagName("ul")
            if (lists.length < 1) {
                list = document.createElement("ul")
                listItem.appendChild(list)
            }
            else {
                list = lists[0]
            }
            return list
        }
        $(document).ready(
                function () {
                    $.ajax("rest/file").done(function (file) {
                        list = document.createElement("ul")
                        $('#treeDiv')[0].appendChild(list)
                        var rootListItem = createListItem(file, list);
                        expand(rootListItem, file.href);
                    })
                }
        )
    </script>
</head>
<body>
<h2>Filesystem Browser</h2>
<div id="treeDiv"/>
</body>
</html>
