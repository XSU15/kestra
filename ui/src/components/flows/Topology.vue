<template>
    <el-card>
        <LowCodeEditor
            v-if="flow"
            :flow-id="flow.id"
            :namespace="flow.namespace"
            :flow-graph="flowGraph"
            :source="flow.source"
            :is-read-only="isReadOnly"
        />
    </el-card>
</template>
<script>
    import {mapState} from "vuex";
    import LowCodeEditor from "../inputs/LowCodeEditor.vue";

    export default {
        components: {
            LowCodeEditor,
        },
        props: {
            preventRouteInfo: {
                type: Boolean,
                default: false
            },
            isReadOnly: {
                type: Boolean,
                default: false
            }
        },
        computed: {
            ...mapState("flow", ["flow", "flowGraph"]),
        },
        beforeUnmount() {
            this.$store.commit("flow/setFlowError", undefined);
        },
    };
</script>
<style scoped>
    el-card {
        height: 100%;
    }
</style>
