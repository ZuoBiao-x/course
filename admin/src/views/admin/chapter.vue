<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>课程ID</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>{{chapter.courseId}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" @click="edit(chapter)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>
                        <button class="btn btn-xs btn-danger" @click="del(chapter.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="5"></pagination>

        <!-- Modal -->
        <div class="modal fade" id="form-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">大章</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="name" placeholder="请输入大章名称..." v-model="chapter.name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="chapterID" class="col-sm-2 control-label">课程ID</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="chapterID" placeholder="请输入大章ID..." v-model="chapter.courseId">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="save">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        name: "Chapter",
        components: {Pagination},
        data: function(){
            return {
                chapters: [],
                chapter: {}
            }
        },
        mounted: function() {
            // sidebar激活样式方法一
            // this.$parent.activeSidebar("business-chapter-sidebar");

            let _this = this;
            _this.$refs.pagination.size = 5;    // 初始化每页记录数
            _this.list(1);                      // 初始化页码
        },
        methods: {
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response)=>{
                    Loading.hide();
                    console.log(response);
                    _this.chapters = response.data.content.list;
                    _this.$refs.pagination.render(page, response.data.content.total);
                })
            },

            add(){
                let _this = this;
                _this.chapter = {};
                    $('#form-modal').modal('show');
            },

            edit(chapter){
                let _this = this;
                /*将列表中对应的chapter拷贝一份，并赋值给_this.chapter，
                这样的话，模态框中显示的数据和列表中的数据是来自两个不同的对象，因此改变模态框中的值不会改变列表中记录的值*/
                _this.chapter = $.extend({}, chapter);
                $('#form-modal').modal('show');
            },

            save(){
                let _this = this;
                Loading.show();
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save', _this.chapter).then((response)=>{
                    Loading.hide();
                    console.log(response);
                    /*保存成功，则隐藏模态框，刷新数据*/
                    if (response.data.success){
                        $('#form-modal').modal('hide');
                        _this.list(1);
                        toast.success("保存成功！");
                    }
                })
            },

            del(id){
                let _this = this;
                Swal.fire({
                    title: '是否删除?',
                    text: "",
                    // type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, delete it!'
                }).then((result) => {
                    if (result.value) {
                        Loading.show();
                        _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response)=>{
                            Loading.hide();
                            if (response.data.success){
                                _this.list(1);
                                toast.success("删除成功！");
                            }
                        })
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>