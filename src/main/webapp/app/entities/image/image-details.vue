<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="image">
        <h2 class="jh-entity-heading" data-cy="imageDetailsHeading">
          <span v-text="$t('dentalApp.image.detail.title')">Image</span> {{ image.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.image.src')">Src</span>
          </dt>
          <dd>
            <span>{{ image.src }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.image.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ image.shopifyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.image.imgPublicId')">Img Public Id</span>
          </dt>
          <dd>
            <span>{{ image.imgPublicId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.image.isAvatar')">Is Avatar</span>
          </dt>
          <dd>
            <span>{{ image.isAvatar }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.image.comboId')">Combo Id</span>
          </dt>
          <dd>
            <span>{{ image.comboId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.image.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ image.productId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="image.id" :to="{ name: 'ImageEdit', params: { imageId: image.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./image-details.component.ts"></script>
