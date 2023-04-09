<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="${param.modalId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <span>${param.message}</span>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                <a class="btn btn-primary" href="${param.url}">Xác nhận</a>
            </div>
        </div>
    </div>
</div>
