Confirm = {
    show: function (message, callback) {
        Swal.fire({
            title: '是否执行此操作？',
            text: message,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                if (callback) {
                    callback()
                }
            }
        })
    }
};