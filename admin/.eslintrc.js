module.exports = {
    root: true,
    env: {
        node: true
    },
    'extends': [
        'plugin:vue/essential',
        'eslint:recommended'
    ],
    rules: {
        'no-console': 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'no-undef': 'off',
        'vue/no-unused-vars': 'off',
        'vue/require-v-for-key': 'off',
        'no-unused-vars': 'off',
        'vue/no-unused-components': 'off',
        //关闭禁止混用tab和空格
        "no-mixed-spaces-and-tabs": [0],
        "no-unused-vars": [2, {
        // 允许声明未使用变量
            "vars": "local",
        // 参数不检查
            "args": "none"
        }],
        // 关闭语句强制分号结尾
        "semi": [0],
    },
    parserOptions: {
        parser: 'babel-eslint'
    }
};
