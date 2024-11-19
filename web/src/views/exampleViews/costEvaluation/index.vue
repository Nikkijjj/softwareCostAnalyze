<template>
    <div class="page-container main-view">
        <GSC ref="GSC"></GSC>
        <CF ref="CF"></CF>
    </div>
</template>

<script>

import {
    defineComponent,
    ref,
} from 'vue';

import GSC from './components/gsc.vue';
import CF from './components/cf.vue';


export default defineComponent({
    components: {
        GSC,
        CF,
    },
    setup() {
        const GSC = ref(null); //组件实例
        const CF = ref(null);

        return {
            GSC,
            CF,
        };
    },
});
</script>

<style lang="scss" scoped>

</style>
