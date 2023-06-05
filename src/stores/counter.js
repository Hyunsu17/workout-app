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
        },
        addToWKList(_idx, _name, _data) {
            this.WKData[_idx].workoutList.push({workoutName: _name, workoutSetData: _data})
        },
        getRoutineIdxByName(_name) {
            for (let i = 0; i < this.WKData.length; i++) {
                if (_name === this.WKData[i].routineName) {
                    return i
                }
            }
            console.log('해당 이름을 가진 루틴이 없습니다')
            return false
        },
        getWKObjectByRoutine(_routineName) {
            for (let i = 0; i < this.WKData.length; i++) {
                console.log(this.WKData[i].routineName)
                if (this.WKData[i].routineName === _routineName)
                    return  this.WKData[i]
            }
            console.log('there is no routine like'+ _routineName +'!')
            return null
        },
        getWKNameByWKIdx(_routineName, _idx){
            console.log("헤헤")
            console.log(_routineName)
            const WKObject = this.getWKObjectByRoutine(_routineName)
            if(WKObject === null){
                console.log('Sorry WorkOut data is undefined')
            }
            return WKObject.workoutList[_idx].workoutName
        }
    }
    ,
    getters: {
        getWKDetailData(state) {
            return state.WKSetData
        },
        getWKData(state) {
            return state.WKData
        }
    }
})
