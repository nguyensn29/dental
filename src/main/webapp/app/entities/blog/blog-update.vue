<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.blog.home.createOrEditLabel"
          data-cy="BlogCreateUpdateHeading"
          v-text="$t('dentalApp.blog.home.createOrEditLabel')"
        >
          Create or edit a Blog
        </h2>
        <div>
          <div class="form-group" v-if="blog.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="blog.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.name')" for="blog-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="blog-name"
              data-cy="name"
              :class="{ valid: !$v.blog.name.$invalid, invalid: $v.blog.name.$invalid }"
              v-model="$v.blog.name.$model"
              required
            />
            <div v-if="$v.blog.name.$anyDirty && $v.blog.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.blog.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.content')" for="blog-content">Content</label>
            <input
              type="text"
              class="form-control"
              name="content"
              id="blog-content"
              data-cy="content"
              :class="{ valid: !$v.blog.content.$invalid, invalid: $v.blog.content.$invalid }"
              v-model="$v.blog.content.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.avatar')" for="blog-avatar">Avatar</label>
            <input
              type="text"
              class="form-control"
              name="avatar"
              id="blog-avatar"
              data-cy="avatar"
              :class="{ valid: !$v.blog.avatar.$invalid, invalid: $v.blog.avatar.$invalid }"
              v-model="$v.blog.avatar.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.imgPublicId')" for="blog-imgPublicId">Img Public Id</label>
            <input
              type="text"
              class="form-control"
              name="imgPublicId"
              id="blog-imgPublicId"
              data-cy="imgPublicId"
              :class="{ valid: !$v.blog.imgPublicId.$invalid, invalid: $v.blog.imgPublicId.$invalid }"
              v-model="$v.blog.imgPublicId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.categoryId')" for="blog-categoryId">Category Id</label>
            <input
              type="number"
              class="form-control"
              name="categoryId"
              id="blog-categoryId"
              data-cy="categoryId"
              :class="{ valid: !$v.blog.categoryId.$invalid, invalid: $v.blog.categoryId.$invalid }"
              v-model.number="$v.blog.categoryId.$model"
              required
            />
            <div v-if="$v.blog.categoryId.$anyDirty && $v.blog.categoryId.$invalid">
              <small class="form-text text-danger" v-if="!$v.blog.categoryId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.blog.categoryId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.blog.isShow')" for="blog-isShow">Is Show</label>
            <input
              type="number"
              class="form-control"
              name="isShow"
              id="blog-isShow"
              data-cy="isShow"
              :class="{ valid: !$v.blog.isShow.$invalid, invalid: $v.blog.isShow.$invalid }"
              v-model.number="$v.blog.isShow.$model"
              required
            />
            <div v-if="$v.blog.isShow.$anyDirty && $v.blog.isShow.$invalid">
              <small class="form-text text-danger" v-if="!$v.blog.isShow.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.blog.isShow.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.blog.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./blog-update.component.ts"></script>
