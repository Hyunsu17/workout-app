import {defineStore} from 'pinia'
import axios from "axios"

export const useRecordStore = defineStore('record', {
    state: () => ({
        WKData: [],
        WKSetData: null
    }),
    actions: {
        setWkDetailData(data) {
            this.WKSetData = data
        },
        pushToWorkoutData(data) {
            this.WKData.push(data)
        },
        postCall(url, params) {
            return axios.post(url, params
            ).catch((error) => {
                console.log(error)
            }).then((rep) => {
                return rep
            })
        },
        hasWkListData(name) {
            for (let i = 0; this.WKData.length; i++) {
                if (this.WKData[i].routineName === name) return true
            }
            return false
        }
    }
    ,
    getters: {
        getWKDetailData(state) {
            return state.WKSetData
        },
        getWKData(state){
            return state.WKData
        }
    }
})
