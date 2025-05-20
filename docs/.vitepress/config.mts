import { defineConfig } from 'vitepress'

export default defineConfig({
  title: "WAuxiliary Plugin",
  description: "WAuxiliary Plugin",
  lang: "zh-Hans",
  base: '/WAuxiliary_Plugin/',
  themeConfig: {
    nav: [
      { text: '首页', link: '/' },
      { text: "接口文档", link: "/api/PluginGlobal", activeMatch: "/api" },
    ],
    sidebar: [
      {
        text: "接口文档",
        collapsed: false,
        items: [
          { text: "全局变量", link: "/api/PluginGlobal" },
          { text: "回调方法", link: "/api/PluginCallback" },
          { text: "配置方法", link: "/api/PluginConfigMethod" },
          { text: "联系方法", link: "/api/PluginContactMethod" },
          { text: "网络方法", link: "/api/PluginHttpMethod" },
          { text: "消息方法", link: "/api/PluginMsgMethod" },
          { text: "其他方法", link: "/api/PluginOtherMethod" },
          { text: "友圈方法", link: "/api/PluginSnsMethod" },
          { text: "相关结构", link: "/api/PluginStruct" },
        ]
      }
    ],
    lastUpdated: {
      text: "最后更新",
      formatOptions: {
        dateStyle: "medium",
        timeStyle: "short"
      }
    },
    docFooter: {
      prev: "上一页",
      next: "下一页"
    },
    search: {
      provider: 'local'
    },
    socialLinks: [
      { icon: 'github', link: 'https://github.com/HdShare/WAuxiliary_Plugin' }
    ],
  }
})
