var app = new Vue({
<<<<<<< HEAD
    el: '#app',
    data: {
        msg: 'xxx',
        servletList: []
    },
    created(){
        this.fetchServletRegistrations();
    },
    methods: {
        submitPoc: function () {
            var data = document.getElementById("poc").value;
            axios({
                method:"post",
                url:"poc",
                params:{
                    poc:data,
                }
            }).then(response=>{
                if(response.data.code == "000000"){
                    this.msg = response.data.message;
                }else{
                    this.msg = 'warrings';
                }
            });
            event.preventDefault();
        },
        fetchServletRegistrations: function (){
            axios({
                method: "get",
                url: "getAllServlet",
            }).then(response => {
                this.servletList = response.data;
=======
    el: "#app",
    data: {
        "Msg": "zzz",
    },
    methods:{
        pocReq: function (){
            var poc = document.getElementById("pocTest").value;
            axios({
                method:"post",
                url:"/poc",
                params:{
                    poc:poc,
                }
            }).then(response=>{
                alert("请求完成");
>>>>>>> e95591830a43e11b62a9e60b0b72dab6d1d99cec
            });
        }
    }
})