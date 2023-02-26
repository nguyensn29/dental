<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="setting">
        <h2 class="jh-entity-heading" data-cy="settingDetailsHeading">
          <span v-text="$t('dentalApp.setting.detail.title')">Setting</span> {{ setting.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.setting.name')">Name</span>
          </dt>
          <dd>
            <span>{{ setting.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.setting.keyName')">Key Name</span>
          </dt>
          <dd>
            <span>{{ setting.keyName }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.setting.value')">Value</span>
          </dt>
          <dd>
            <span>{{ setting.value }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.setting.rule')">Rule</span>
          </dt>
          <dd>
            <span>{{ setting.rule }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.setting.isNumber')">Is Number</span>
          </dt>
          <dd>
            <span>{{ setting.isNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.setting.isObject')">Is Object</span>
          </dt>
          <dd>
            <span>{{ setting.isObject }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="setting.id" :to="{ name: 'SettingEdit', params: { settingId: setting.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./setting-details.component.ts"></script>
