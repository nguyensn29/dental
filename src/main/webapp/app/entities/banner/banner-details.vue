<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="banner">
        <h2 class="jh-entity-heading" data-cy="bannerDetailsHeading">
          <span v-text="$t('dentalApp.banner.detail.title')">Banner</span> {{ banner.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.banner.name')">Name</span>
          </dt>
          <dd>
            <span>{{ banner.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.banner.src')">Src</span>
          </dt>
          <dd>
            <span>{{ banner.src }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.banner.isShow')">Is Show</span>
          </dt>
          <dd>
            <span>{{ banner.isShow }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.banner.imgPublicId')">Img Public Id</span>
          </dt>
          <dd>
            <span>{{ banner.imgPublicId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="banner.id" :to="{ name: 'BannerEdit', params: { bannerId: banner.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./banner-details.component.ts"></script>
