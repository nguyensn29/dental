<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="blog">
        <h2 class="jh-entity-heading" data-cy="blogDetailsHeading">
          <span v-text="$t('dentalApp.blog.detail.title')">Blog</span> {{ blog.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.blog.name')">Name</span>
          </dt>
          <dd>
            <span>{{ blog.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.blog.content')">Content</span>
          </dt>
          <dd>
            <span>{{ blog.content }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.blog.avatar')">Avatar</span>
          </dt>
          <dd>
            <span>{{ blog.avatar }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.blog.imgPublicId')">Img Public Id</span>
          </dt>
          <dd>
            <span>{{ blog.imgPublicId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.blog.categoryId')">Category Id</span>
          </dt>
          <dd>
            <span>{{ blog.categoryId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.blog.isShow')">Is Show</span>
          </dt>
          <dd>
            <span>{{ blog.isShow }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="blog.id" :to="{ name: 'BlogEdit', params: { blogId: blog.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./blog-details.component.ts"></script>
