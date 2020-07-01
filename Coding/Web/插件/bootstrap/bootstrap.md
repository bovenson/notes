# 模态框

## 确定/取消模态框

```html
<!-- 确认/取消 模态框 -->
<div id="confirmCancelModal" class="modal fade" role="dialog">>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">&times;</button>
                <h4 id="confirmCancelModalTitle" class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <p id="confirmCancelModalText"></p>
            </div>
            <div class="modal-footer">
                <button id="confirmCancelModalConfirm" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>	
```

```javascript
/* 确定/取消模态框 */
function showConfirmOrCancelModal(title, msg, confirm, cancel) {
    var confirmCancelModal = $("#confirmCancelModal");
    var confirmCancelModalTitle = $("#confirmCancelModalTitle");
    var confirmCancelModalText = $("#confirmCancelModalText");
    var confirmCancelModalConfirm = $("#confirmCancelModalConfirm");

    confirmCancelModalTitle.text(title);
    confirmCancelModalText.text(msg);
    confirmCancelModalConfirm.unbind('click');
    // 点击确定, 回调函数
    confirmCancelModalConfirm.click(confirm);
    confirmCancelModal.modal("show");
}
```

