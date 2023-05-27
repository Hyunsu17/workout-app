import {defineStore} from 'pinia'
import axios from "axios"

export const useRecordStore = defineStore('record', {
    state: () => ({
        routineName: '',
        workOutData: [],
        wkSetData: null
    }),
    actions: {
        setData(data) {
            this.wkSetData = data
        },
        setWorkoutData(data) {
            this.workOutData = data
        }
        ,
        postCall(url, params) {
            return axios.post(url, params
            ).catch((error) => {
                console.log(error)
            }).then((rep) => {
                return rep
            })
        }
    }
    ,
    getters: {
        getData(state) {
            return state.wkSetData
        }
    }
})
