<template>
    <div>
        <H3>您好，欢迎登陆控台！</H3>
    </div>
</template>

<script>
    export default {
        name: "Welcome",
        /*用于在组件被渲染的时候，告诉admin父组件，需要给哪个菜单设置样式*/
        mounted: function() {
            // sidebar激活样式方法一
            // this.$parent.activeSidebar("welcome-sidebar");
        },
        methods: {
        }
    }
</script>

<style scoped>

</style>