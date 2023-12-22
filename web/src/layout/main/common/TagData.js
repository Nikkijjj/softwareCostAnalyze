/** 
 * 储存tag的数据
 * 管理小型数据，刚好适用于管理当前layout的数据
 */
import { 
    reactive,
} from 'vue';
import allStorage from "@/action/StorageManage";

/**
 * 优先使用缓存中的数据
 */
const mainTagListStorage = allStorage.mainTagListStorage();
let tagList = mainTagListStorage.value;
if(!Array.isArray(tagList)) {
    tagList = [];
}
/** 数据源 */
const TagData = {
    tagList:tagList,  //标签列表
    activeSign:'',  //当前活动的标签唯一标识
    tagHisList:[],  //标签历史列表，记录标签历史添加列表
    /** 写入tagData */
    setTagList(value){
        this.tagList = value || [];
        /** 存入缓存 */
        mainTagListStorage.value = value;
    },
    /** 写入当前活动的 */
    setActiveSign(value){
        this.activeSign = value || '';
    },
    /** 写入标签历史列表，用来删除切换上一个标签时更加人性化一点 */
    setTagHisList(value){
        this.tagHisList = value || [];
    },
};

/**
 * 一个标签的属性如下
 * 其中sign是唯一标识，很重要，必填 
 * menuName 必填
 * */

export default reactive(TagData);