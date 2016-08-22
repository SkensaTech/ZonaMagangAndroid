package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_bidang_sekolah
{
  private java.util.Date updated;
  private String ownerId;
  private String objectId;
  private Integer id_bidang;
  private Integer id_sekolah;
  private java.util.Date created;
  public Integer id_bidang_sekolah;
  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public Integer getId_bidang()
  {
    return id_bidang;
  }

  public void setId_bidang( Integer id_bidang )
  {
    this.id_bidang = id_bidang;
  }

  public Integer getId_sekolah()
  {
    return id_sekolah;
  }

  public void setId_sekolah( Integer id_sekolah )
  {
    this.id_sekolah = id_sekolah;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public Integer getId_bidang_sekolah()
  {
    return id_bidang_sekolah;
  }

  public void setId_bidang_sekolah( Integer id_bidang_sekolah )
  {
    this.id_bidang_sekolah = id_bidang_sekolah;
  }

                                                    
  public tb_bidang_sekolah save()
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).save( this );
  }

  public Future<tb_bidang_sekolah> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_sekolah> future = new Future<tb_bidang_sekolah>();
      Backendless.Data.of( tb_bidang_sekolah.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_bidang_sekolah> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( tb_bidang_sekolah.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).remove( this, callback );
  }

  public static tb_bidang_sekolah findById( String id )
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).findById( id );
  }

  public static Future<tb_bidang_sekolah> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_sekolah> future = new Future<tb_bidang_sekolah>();
      Backendless.Data.of( tb_bidang_sekolah.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_bidang_sekolah> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).findById( id, callback );
  }

  public static tb_bidang_sekolah findFirst()
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).findFirst();
  }

  public static Future<tb_bidang_sekolah> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_sekolah> future = new Future<tb_bidang_sekolah>();
      Backendless.Data.of( tb_bidang_sekolah.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_bidang_sekolah> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).findFirst( callback );
  }

  public static tb_bidang_sekolah findLast()
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).findLast();
  }

  public static Future<tb_bidang_sekolah> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_sekolah> future = new Future<tb_bidang_sekolah>();
      Backendless.Data.of( tb_bidang_sekolah.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_bidang_sekolah> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_bidang_sekolah> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_bidang_sekolah.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_bidang_sekolah>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_bidang_sekolah>> future = new Future<BackendlessCollection<tb_bidang_sekolah>>();
      Backendless.Data.of( tb_bidang_sekolah.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_bidang_sekolah>> callback )
  {
    Backendless.Data.of( tb_bidang_sekolah.class ).find( query, callback );
  }
}