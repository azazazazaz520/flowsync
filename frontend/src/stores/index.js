import { defineStore } from 'pinia'

export const useMainStore = defineStore('main', {
  state: () => ({
    token: '',
    currentUser: {
      id: null,
      username: '',
      realName: '',
      role: ''
    }
  }),

  getters: {
    isLeader: (state) => state.currentUser.role === '负责人',
    currentUserId: (state) => state.currentUser.id
  },

  actions: {
    setUserInfo(data) {
      this.token = data.token || ''
      this.currentUser = {
        id: data.id,
        username: data.username,
        realName: data.realName,
        role: data.role
      }
      sessionStorage.setItem('token', this.token)
      sessionStorage.setItem('currentUser', JSON.stringify(this.currentUser))
    },

    loadFromStorage() {
      const token = sessionStorage.getItem('token')
      const user = sessionStorage.getItem('currentUser')
      if (token && user) {
        this.token = token
        this.currentUser = JSON.parse(user)
      }
    },

    clearUser() {
      this.token = ''
      this.currentUser = { id: null, username: '', realName: '', role: '' }
      sessionStorage.clear()
    }
  }
})
